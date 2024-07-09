package ru.academits.gerasimenko.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (from >= range.to || to <= range.from) {
            return null;
        }

        if (from >= range.from && to <= range.to) {
            return this;
        }

        if (from <= range.from && to >= range.to) {
            return range;
        }

        if (from < range.from && to < range.to) {
            return new Range(range.from, to);
        }

        return new Range(from, range.to);
    }

    public Range[] getUnion(Range range) {
        if (to < range.from) {
            return new Range[]{this, range};
        }

        if (from > range.to) {
            return new Range[]{range, this};
        }

        if (from <= range.from && to >= range.to) {
            return new Range[]{this};
        }

        if (from >= range.from && to <= range.to) {
            return new Range[]{range};
        }

        if (from < range.from) {
            return new Range[]{new Range(from, range.to)};
        }

        return new Range[]{new Range(range.from, to)};
    }

    public Range[] getDifference(Range range) {
        if (from >= range.from && to <= range.to) {
            return null;
        }

        if (from >= range.to || to <= range.from) {
            return new Range[]{this};
        }

        if (from < range.from && to < range.to) {
            return new Range[]{new Range(from, range.from)};
        }

        if (from > range.from && to > range.to) {
            return new Range[]{new Range(range.to, to)};
        }

        return new Range[]{new Range(from, range.from), new Range(range.to, to)};
    }

    public static void print(Range range) {
        System.out.printf("[%f; %f]%n", range.from, range.to);
    }
}