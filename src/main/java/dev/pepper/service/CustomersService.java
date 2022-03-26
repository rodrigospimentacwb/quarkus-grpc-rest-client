package dev.pepper.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import dev.pepper.grpc.customer.CustomersServiceGrpc.CustomersServiceBlockingStub;
import dev.pepper.grpc.customer.Multiplyer;
import dev.pepper.model.Customer;
import io.quarkus.grpc.GrpcClient;

@ApplicationScoped
public class CustomersService {

    @GrpcClient("grpcCustomers")
    CustomersServiceBlockingStub blockingStub;

    public List<Customer> listAllByGRPC (Integer multiplyer) {
        List<dev.pepper.grpc.customer.Customer> list = blockingStub.listAll(Multiplyer.newBuilder().setValue(multiplyer).build()).getCustomerList();
        return fromCustomer(list);
    }

    private List<Customer> fromCustomer (List<dev.pepper.grpc.customer.Customer> list) {
        return list.stream()
                .map(this::fromCustomer)
                .collect(Collectors.toList());
    }

    private Customer fromCustomer (dev.pepper.grpc.customer.Customer customerGRPC) {
        return new Customer(
                customerGRPC.getId(),
                customerGRPC.getName(),
                customerGRPC.getEmail(),
                customerGRPC.getPhone(),
                customerGRPC.getAddress(),
                customerGRPC.getZipCode(),
                customerGRPC.getCpf()
        );
    }

    public String findNameById(Long id) {
        return blockingStub.getNameById(
                dev.pepper.grpc.customer.Customer.newBuilder()
                        .setId(id)
                        .build())
                .getCustomerName();
    }
}
