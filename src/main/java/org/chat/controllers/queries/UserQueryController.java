package org.chat.controllers.queries;

import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.responsetypes.InstanceResponseType;
import org.chat.model.User;
import org.chat.model.queries.UserGeByIdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

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
    public Future<User> get(@PathVariable String id) {
        UserGeByIdQuery query = new UserGeByIdQuery();
        query.setId(id);
        return queryGateway.query(query, new InstanceResponseType<>(User.class));
    }

}
