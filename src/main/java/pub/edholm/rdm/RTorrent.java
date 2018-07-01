package pub.edholm.rdm;

import de.timroes.axmlrpc.XMLRPCClient;
import de.timroes.axmlrpc.XMLRPCException;
import pub.edholm.rdm.domain.Torrent;
import pub.edholm.rdm.exceptions.CommandException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class RTorrent {
    private static final String RPC_URL = "http://localhost/RPC2";
    private final XMLRPCClient rpcClient;

    RTorrent() {
        try {
            rpcClient = new XMLRPCClient(new URL(RPC_URL));
        } catch (MalformedURLException e) {
            throw new AssertionError(RPC_URL + " is malformed", e);
        }
    }

    List<Torrent> getAllTorrents() {
        final List<String> methods = List.of("", "default", "d.hash=", "d.name=", "d.directory=", "d.custom1=");
        try {
            final Object[] results = (Object[]) rpcClient.call("d.multicall2", methods.toArray());
            return Arrays.stream(results)
                    .map(this::convertResultToTorrent)
                    .collect(Collectors.toList());
        } catch (XMLRPCException e) {
            final String commaMethods = methods.stream().collect(Collectors.joining(", "));
            throw new CommandException("Unable to execute d.multicall2 with methods: " + commaMethods, e);
        }
    }

    private Torrent convertResultToTorrent(Object result) {
        final Object[] resultArray = validateRpcResult(result);

        final String hash = resultArray[0].toString();
        final String name = resultArray[1].toString();
        final String directory = resultArray[2].toString();
        final String label = resultArray[3].toString();

        return Torrent.valueOf(hash, name, directory, label);
    }

    private Object[] validateRpcResult(Object result) {
        if (result == null) {
            throw new NullPointerException("Resulting torrent may not be null");
        }
        final Object[] resultArray = (Object[]) result;
        if (resultArray.length != 4) {
            throw new IllegalArgumentException("Wrong number of fields. Expected 4, got " + resultArray.length);
        }
        return resultArray;
    }
}
