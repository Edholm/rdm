package pub.edholm.rdm.domain;

import java.util.Objects;

public final class TorrentHash {
    private static final int HASH_LENGTH = 40;
    private final String hash;

    private TorrentHash(String hash) {
        this.hash = hash;
    }

    public static TorrentHash valueOf(String torrentHash) {
        if (torrentHash == null || torrentHash.length() != HASH_LENGTH) {
            throw new IllegalArgumentException("Invalid torrent hash: " + torrentHash);
        }
        return new TorrentHash(torrentHash);
    }

    public static TorrentHash valueOf(Object torrentHash) {
        return valueOf(torrentHash.toString());
    }

    @Override
    public String toString() {
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TorrentHash that = (TorrentHash) o;
        return Objects.equals(hash, that.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash);
    }
}
