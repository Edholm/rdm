package pub.edholm.rdm;

import pub.edholm.rdm.domain.Torrent;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        final RTorrent rTorrent = new RTorrent();
        final List<Torrent> allTorrents = rTorrent.getAllTorrents();

        final TorrentMatcher matcher = new TorrentMatcher(allTorrents);
        Arrays.stream(args)
            .filter(arg -> !matcher.isTorrentDir(arg))
            .map(Application::escapeIllegalChars)
            .forEach(System.out::println);
    }

    private static String escapeIllegalChars(String path) {
        return path.replace(" ", "\\ ")
            .replace("'", "\'");
    }

    private static class TorrentMatcher {
        private final List<Torrent> torrents;

        TorrentMatcher(List<Torrent> torrents) {
            this.torrents = torrents;
        }

        boolean isTorrentDir(String dir) {
            return torrents.stream()
                .anyMatch(t -> t.getFullPath().toString().contentEquals(dir));
        }
    }
}
