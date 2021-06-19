package renderer;

import elements.*;
import scene.Scene;
import primitives.*;

import java.util.MissingResourceException;

public class Render {
    ImageWriter imageWriter;
    Camera camera;
    RayTracerBase rayTracer;

    private static final String RESOURCE_ERROR = "Renderer resource not set";
    private static final String RENDER_CLASS = "Render";
    private static final String IMAGE_WRITER_COMPONENT = "Image writer";
    private static final String CAMERA_COMPONENT = "Camera";
    private static final String RAY_TRACER_COMPONENT = "Ray tracer";

    private int threadsCount = 0;
    private static final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
    private boolean print = false; // printing progress percentage

    public Render setMultithreading(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
        if (threads != 0)
            this.threadsCount = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            this.threadsCount = cores <= 2 ? 1 : cores;
        }
        return this;
    }
    public Render setDebugPrint() {
        print = true;
        return this;
    }


    private class Pixel{
        private long maxRows = 0;
        private long maxCols = 0;
        private long pixels = 0;
        public volatile int row = 0;
        public volatile int col = -1;
        private long counter = 0;
        private int percents = 0;
        private long nextCounter = 0;

        /**
         * The constructor for initializing the main follow up Pixel object
         *
         * @param maxRows the amount of pixel rows
         * @param maxCols the amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
        this.pixels = (long) maxRows * maxCols;
        this.nextCounter = this.pixels / 100;
        if (Render.this.print)
            System.out.printf("\r %02d%%", this.percents);
    }

        /**
         * Default constructor for secondary Pixel objects
         */
        public Pixel() {
    }

        /**
         * Internal function for thread-safe manipulating of main follow up Pixel object
         * - this function is critical section for all the threads, and main Pixel
         * object data is the shared data of this critical section.<br/>
         * The function provides next pixel number each call.
         *
         * @param target target secondary Pixel object to copy the row/column of the
         *               next pixel
         * @return the progress percentage for follow up: if it is 0 - nothing to print,
         *         if it is -1 - the task is finished, any other value - the progress
         *         percentage (only when it changes)
         */
        private synchronized int nextP(Pixel target) {
        ++col;
        ++this.counter;
        if (col < this.maxCols) {
            target.row = this.row;
            target.col = this.col;
            if (Render.this.print && this.counter == this.nextCounter) {
                ++this.percents;
                this.nextCounter = this.pixels * (this.percents + 1) / 100;
                return this.percents;
            }
            return 0;
        }
        ++row;
        if (row < this.maxRows) {
            col = 0;
            target.row = this.row;
            target.col = this.col;
            if (Render.this.print && this.counter == this.nextCounter) {
                ++this.percents;
                this.nextCounter = this.pixels * (this.percents + 1) / 100;
                return this.percents;
            }
            return 0;
        }
        return -1;
    }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         *
         * @param target target secondary Pixel object to copy the row/column of the
         *               next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
        int percent = nextP(target);
        if (Render.this.print && percent > 0)
            synchronized (this) {
                notifyAll();
            }
        if (percent >= 0)
            return true;
        if (Render.this.print)
            synchronized (this) {
                notifyAll();
            }
        return false;
    }

        /**
         * Debug print of progress percentage - must be run from the main thread
         */
        public void print() {
        if (Render.this.print)
            while (this.percents < 100)
                try {
                    synchronized (this) {
                        wait();
                    }
                    System.out.printf("\r %02d%%", this.percents);
                    System.out.flush();
                } catch (Exception e) {
                }
    }
    }
    /**
     * Image, RayTracer,Camera... implementations
     * @param
     * @return
     */

    public Render setImageWriter(ImageWriter _imageWriter) {
        imageWriter = _imageWriter;
        return this;
    }


    public Render setCamera(Camera _camera) {
        camera = _camera;
        return this;
    }

    public Render setRayTracer(RayTracerBase _rayTracer) {
        this.rayTracer = _rayTracer;
        return this;
    }

    public void renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            //if (scene == null) {
            //   throw new MissingResourceException("missing resource", Scene.class.getName(), "");
            //  }
            if (camera == null) {
                throw new MissingResourceException("missing resource", Camera.class.getName(), "");
            }
            if (rayTracer == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }

            //rendering the image
            int nX = imageWriter.getNx() * 2;
            int nY = imageWriter.getNy() * 2;
            if (threadsCount == 0) {
                for (int i = 0; i < nY; i += 2) {
                    for (int j = 0; j < nX; j += 2) {
                        Ray rays[] = {camera.constructRayThroughPixel(nX, nY, j + 1, i),
                                camera.constructRayThroughPixel(nX, nY, j, i + 1),
                                camera.constructRayThroughPixel(nX, nY, j + 1, i + 1),
                                camera.constructRayThroughPixel(nX, nY, j, i)};

                        Color pixelColor = Color.BLACK;

                        for (Ray r : rays) {
                            pixelColor = pixelColor.add(rayTracer.traceRay(r));
                        }


                        imageWriter.writePixel(j / 2, i / 2, pixelColor.reduce(4));
                    }

                }
            } else
                renderImageThreaded();
        }  catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }


    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("missing imageWriter", ImageWriter.class.getName(), "");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nX; i += interval) {
            for (int j = 0; j < nY; j++) {
                imageWriter.writePixel(i, j, color);
            }
        }
        for (int i = 0; i < nY; i += interval) {
            for (int j = 0; j < nX; j++) {
                imageWriter.writePixel(j, i, color);
            }
        }
    }

    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("missing imageWriter", ImageWriter.class.getName(), "");
        imageWriter.writeToImage();
    }


        private void castRay(int nX, int nY, int col, int row) {
col*=2;
row*=2;
            nX*=2;
            nY*=2;
                    Ray rays[] = {camera.constructRayThroughPixel(nX, nY, col + 1, row),
                            camera.constructRayThroughPixel(nX, nY, col, row + 1),
                            camera.constructRayThroughPixel(nX, nY, col + 1, row + 1),
                            camera.constructRayThroughPixel(nX, nY, col, row)};

                    Color pixelColor = Color.BLACK;

                    for (Ray r : rays) {
                        pixelColor = pixelColor.add(rayTracer.traceRay(r));
                    }
            imageWriter.writePixel((long)(col+1) / 2, row / 2, pixelColor.reduce(4));
            imageWriter.writePixel(col / 2, (row+1) / 2, pixelColor.reduce(4));
            imageWriter.writePixel((col+1) / 2, (row+1) / 2, pixelColor.reduce(4));
            imageWriter.writePixel(col / 2, row / 2, pixelColor.reduce(4));


            }


            private void renderImageThreaded () {
                final int nX = imageWriter.getNx();
                final int nY = imageWriter.getNy();
                final Pixel thePixel = new Pixel(nY, nX);
                // Generate threads
                Thread[] threads = new Thread[threadsCount];
                for (int x = threadsCount - 1; x >= 0; --x) {
                    threads[x] = new Thread(() -> {
                        Pixel pixel = new Pixel();
                        while (thePixel.nextPixel(pixel))
                            castRay(nX, nY, pixel.col, pixel.row);
                    });
                }
                // Start threads
                for (Thread thread : threads)
                    thread.start();

                // Print percents on the console
                thePixel.print();

                // Ensure all threads have finished
                for (Thread thread : threads)
                    try {
                        thread.join();
                    } catch (Exception e) {
                    }

                if (print)
                    System.out.print("\r100%");
            }
}
