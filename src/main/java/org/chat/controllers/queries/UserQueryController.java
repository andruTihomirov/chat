package org.chat.controllers.queries;

import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.responsetypes.ResponseTypes;
import org.chat.model.User;
import org.chat.model.queries.UserGetAllQuery;
import org.chat.model.queries.UserGetByIdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author atsikhamirau on 24.05.2019
 */
@RestController
@RequestMapping("users")
public class UserQueryController {

    private final QueryGateway queryGateway;

    @Autowired
    public UserQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable String id) {
        UserGetByIdQuery query = new UserGetByIdQuery();
        query.setId(id);
        return queryGateway.query(query, ResponseTypes.instanceOf(User.class)).join();
    }

    @GetMapping
    public List<User> getAll() {
        return queryGateway.query(new UserGetAllQuery(), ResponseTypes.multipleInstancesOf(User.class)).join();
    }

}
