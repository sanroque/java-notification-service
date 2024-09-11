package tech.sanroque.magalums.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import tech.sanroque.magalums.entity.Channel;
import tech.sanroque.magalums.entity.Status;
import tech.sanroque.magalums.repository.ChannelRepository;
import tech.sanroque.magalums.repository.StatusRepository;

import java.util.Arrays;

@AllArgsConstructor
@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream( Channel.Values.values())
                .map(Channel.Values::toChannel)
                .forEach(channelRepository::save);

        Arrays.stream(Status.Values.values())
                .map(Status.Values::toStatus)
                .forEach(statusRepository::save);
    }
}
