import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class InputFactory {
    public void command(String inputs[], AbstractMap<Integer, InputBase> inputCollection) {
        List<Stream> streams;
        User u;
        int type;
        switch(inputs[1]) {
            case "ADD":
                String name = "";
                for(int i = 6; i < inputs.length; i++) {
                    name += inputs[i];
                    if(i != inputs.length - 1)
                        name += " ";
                }

                inputCollection.put(Integer.parseInt(inputs[3]), new Stream(Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]),
                        Integer.parseInt(inputs[4]), 0, Integer.parseInt(inputs[0]), Long.parseLong(inputs[5]),
                        System.currentTimeMillis() / 1000, name, inputCollection));
                break;
            case "DELETE":
                if(inputCollection.get(Integer.parseInt(inputs[2])) instanceof Stream) {
                    for(Integer k: inputCollection.keySet()) {
                        if(inputCollection.get(k) instanceof User &&
                                ((User) inputCollection.get(k)).hasListenedTo((Stream)inputCollection.get(Integer.parseInt(inputs[2])))) {
                            ((User) inputCollection.get(k)).delete(Integer.parseInt(inputs[2]));
                        }
                    }
                    inputCollection.remove(Integer.parseInt(inputs[2]));
                }
                break;
            case "LIST":
                ListStrategy listStrategy = (ListStrategy) inputCollection.get(Integer.parseInt(inputs[0]));
                listStrategy.list(inputCollection);
                break;
            case "LISTEN":
                InputBase user = inputCollection.get(Integer.parseInt(inputs[0]));
                InputBase stream = inputCollection.get(Integer.parseInt(inputs[2]));
                if(user instanceof User && stream instanceof Stream) {
                    ((Stream)stream).listen();
                    ((User)user).listen((Stream)stream);
                }
                break;
            case "RECOMMEND":
                u = (User) inputCollection.get(Integer.parseInt(inputs[0]));
                type = 1;
                switch(inputs[2]) {
                    case "SONG":
                        type = 1;
                        break;
                    case "PODCAST":
                        type = 2;
                        break;
                    case "AUDIOBOOK":
                        type = 3;
                        break;
                }
                RecommendIterator recommendIterator = new RecommendIterator(u, type, inputCollection);
                streams = new ArrayList<>();
                for(int i = 0; i < 5; i++) {
                    if(recommendIterator.hasNext()) {
                        streams.add(recommendIterator.next());
                    }
                }
                System.out.println(ProiectPOO.JSONOutput(streams.toArray()));
                break;
            case "SURPRISE":
                u = (User) inputCollection.get(Integer.parseInt(inputs[0]));
                type = 1;
                switch(inputs[2]) {
                    case "SONG":
                        type = 1;
                        break;
                    case "PODCAST":
                        type = 2;
                        break;
                    case "AUDIOBOOK":
                        type = 3;
                        break;
                }
                SurpriseIterator surpriseIterator = new SurpriseIterator(u, type, inputCollection);
                streams = new ArrayList<>();
                for(int i = 0; i < 3; i++) {
                    if(surpriseIterator.hasNext()) {
                        streams.add(surpriseIterator.next());
                    }
                }
                System.out.println(ProiectPOO.JSONOutput(streams.toArray()));
                break;
        }
    }

    public void input(String type, String[] values, AbstractMap<Integer, InputBase> inputCollection) {
        InputBase in;
        switch(type) {
            case "stream":
                in = new Stream(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]),
                        Long.parseLong(values[3]), Integer.parseInt(values[4]), Long.parseLong(values[5]),
                        Long.parseLong(values[6]), values[7], inputCollection);
                break;
            case "streamer":
                in = new Streamer(Integer.parseInt(values[0]), Integer.parseInt(values[1]), values[2]);
                break;
            case "user":
                in = new User(Integer.parseInt(values[0]), values[1]);
                String[] streamIds = values[2].split(" ");
                for (String streamId: streamIds) {
                    ((User) in).addStream(Integer.parseInt(streamId));
                }
                break;
            default:
                return;
        }
        inputCollection.put(in.getId(), in);
    }
}
