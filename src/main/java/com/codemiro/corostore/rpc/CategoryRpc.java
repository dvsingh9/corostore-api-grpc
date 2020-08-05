package com.codemiro.corostore.rpc;

import com.codemiro.corostore.catalog.*;
import com.codemiro.corostore.repository.entity.Category;
import com.codemiro.corostore.service.CategoryService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;


@GRpcService
public class CategoryRpc extends CategoryServiceGrpc.CategoryServiceImplBase {

    private final CategoryService categoryService;

    public CategoryRpc(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * <pre>
     * rpc create category (CategoryRequest) returns (stream CategoryRequest);
     * </pre>
     */
    public void save(CategoryRequest request, StreamObserver<CategoryResponse> responseObserver) {
           Category savedCategory = categoryService.create(request);
           responseObserver.onNext(CategoryResponse.newBuilder()
                   .setName(savedCategory.getName())
                   .setId(savedCategory.getId().toString())
                   .build());
           responseObserver.onCompleted();
    }

    /**
     */
    public void getAll(com.codemiro.corostore.catalog.CategoryResponse request, StreamObserver<CategoryResponse> responseObserver) {
        categoryService.getAll().forEach(category -> {
            responseObserver.onNext(
                    CategoryResponse.newBuilder()
                            .setName(category.getName())
                            .setId(category.getId().toString())
                            .setAudit(Audit.newBuilder().setIsDelete(category.getIsDelete()).build())
                            .build()
            );
        });
        responseObserver.onCompleted();
    }



}
