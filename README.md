# rdm

The application takes a list of directories as input and prints all supplied directories that does not match a torrent in rtorrent. 

This is useful for removing untied torrents. E.g.
```
rdm /mnt/storage/TV-Shows/* | xargs rm -r
```

*Note that the directories must be supplied in absolute form.*
