package org.chobit.jspy.core.constants;

public enum StorageUnit {

    /**
     * Byte size
     */
    B {
        @Override
        public long toBytes(long size) {
            return size;
        }

        @Override
        public long toKB(long size) {
            return size / _KB;
        }

        @Override
        public long toMB(long size) {
            return size / _MB;
        }

        @Override
        public long toGB(long size) {
            return size / _GB;
        }

        @Override
        public long toTB(long size) {
            return size / _TB;
        }

        @Override
        public long toPB(long size) {
            return size / _PB;
        }
    },

    /**
     * Kilo Bytes
     */
    KB {
        @Override
        public long toBytes(long size) {
            return size * _KB;
        }

        @Override
        public long toKB(long size) {
            return size;
        }

        @Override
        public long toMB(long size) {
            return size * _KB / _MB;
        }

        @Override
        public long toGB(long size) {
            return size * _KB / _GB;
        }

        @Override
        public long toTB(long size) {
            return size * _KB / _TB;
        }

        @Override
        public long toPB(long size) {
            return size * _KB / _PB;
        }
    },

    /**
     * Mega Bytes
     */
    MB {
        @Override
        public long toBytes(long size) {
            return size * _MB;
        }

        @Override
        public long toKB(long size) {
            return size * _MB / _KB;
        }

        @Override
        public long toMB(long size) {
            return size;
        }

        @Override
        public long toGB(long size) {
            return size * _MB / _GB;
        }

        @Override
        public long toTB(long size) {
            return size * _MB / _TB;
        }

        @Override
        public long toPB(long size) {
            return size * _MB / _TB;
        }
    },

    /**
     * Giga Bytes
     */
    GB {
        @Override
        public long toBytes(long size) {
            return size * _GB;
        }

        @Override
        public long toKB(long size) {
            return size * _GB / _KB;
        }

        @Override
        public long toMB(long size) {
            return size * _GB / _MB;
        }

        @Override
        public long toGB(long size) {
            return size;
        }

        @Override
        public long toTB(long size) {
            return size * _GB / _TB;
        }

        @Override
        public long toPB(long size) {
            return size * _GB / _PB;
        }
    },

    /**
     * Tera Bytes
     */
    TB {
        @Override
        public long toBytes(long size) {
            return size * _TB;
        }

        @Override
        public long toKB(long size) {
            return size * _TB / _KB;
        }

        @Override
        public long toMB(long size) {
            return size * _TB / _MB;
        }

        @Override
        public long toGB(long size) {
            return size * _TB / _GB;
        }

        @Override
        public long toTB(long size) {
            return size;
        }

        @Override
        public long toPB(long size) {
            return size * _TB / _PB;
        }
    },

    /**
     * Peta Bytes
     */
    PB {
        @Override
        public long toBytes(long size) {
            return size * _PB;
        }

        @Override
        public long toKB(long size) {
            return size * _PB / _KB;
        }

        @Override
        public long toMB(long size) {
            return size * _PB / _MB;
        }

        @Override
        public long toGB(long size) {
            return size * _PB / _GB;
        }

        @Override
        public long toTB(long size) {
            return size * _PB / _TB;
        }

        @Override
        public long toPB(long size) {
            return size;
        }
    },
    ;


    private static final long _B = 1L;

    private static final long _KB = _B * 1024;

    private static final long _MB = _KB * 1024;

    private static final long _GB = _MB * 1024;

    private static final long _TB = _GB * 1024;

    private static final long _PB = _TB * 1024;


    public long toBytes(long size) {
        throw new AbstractMethodError();
    }

    public long toKB(long size) {
        throw new AbstractMethodError();
    }

    public long toMB(long size) {
        throw new AbstractMethodError();
    }

    public long toGB(long size) {
        throw new AbstractMethodError();
    }

    public long toTB(long size) {
        throw new AbstractMethodError();
    }

    public long toPB(long size) {
        throw new AbstractMethodError();
    }
}
