package visumap.Statistic;

class SystemTimeSupplier implements TimeSupplier {
    public long getNanoseconds() {
        return System.nanoTime();
    }
}
