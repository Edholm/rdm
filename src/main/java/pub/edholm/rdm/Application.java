package pub.edholm.rdm;

import pub.edholm.rdm.domain.Torrent;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        final RTorrent rTorrent = new RTorrent();
        final List<Torrent> allTorrents = rTorrent.getAllTorrents();

        final TorrentMatcher matcher = new TorrentMatcher(allTorrents);
        for (String arg : args) {
            if (!matcher.isTorrentDir(arg)) {
                System.out.println(arg);
            }
        }
    }

    private static class TorrentMatcher {
        private final List<Torrent> torrents;

        public TorrentMatcher(List<Torrent> torrents) {
            this.torrents = torrents;
        }

        public boolean isTorrentDir(String dir) {
            return torrents.stream()
                    .anyMatch(t -> t.getFullPath().toString().contentEquals(dir));
        }
    }
}
