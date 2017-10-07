package pub.edholm.rdm.domain;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public final class Torrent {
    private final TorrentHash hash;
    private final String name;
    private final Path fullPath;
    private final String label;

    private Torrent(TorrentHash hash, String name, Path fullPath, String label) {
        this.hash = hash;
        this.name = name;
        this.fullPath = fullPath;
        this.label = label;
    }

    public static Torrent valueOf(String hash, String name, String directory, String label) {
        return new Torrent(TorrentHash.valueOf(hash), name, Paths.get(directory), label);
    }

    public TorrentHash getHash() {
        return hash;
    }

    public String getName() {
        return name;
    }

    public Path getFullPath() {
        return fullPath;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Torrent torrent = (Torrent) o;
        return Objects.equals(hash, torrent.hash) &&
                Objects.equals(name, torrent.name) &&
                Objects.equals(fullPath, torrent.fullPath) &&
                Objects.equals(label, torrent.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, name, fullPath, label);
    }

    @Override
    public String toString() {
        return "Torrent{" +
                "hash=" + hash +
                ", name='" + name + '\'' +
                ", fullPath=" + fullPath +
                ", label='" + label + '\'' +
                '}';
    }
}
