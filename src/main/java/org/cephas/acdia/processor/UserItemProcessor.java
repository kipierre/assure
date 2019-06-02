package org.cephas.acdia.processor;
import org.springframework.batch.item.ItemProcessor;
import org.cephas.acdia.model.User;

/**
 * Created by admin on 30-05-19.
 */
public class UserItemProcessor implements ItemProcessor<User, User>{

    @Override
    public User process(User item) throws Exception {
        return item;
    }
}
