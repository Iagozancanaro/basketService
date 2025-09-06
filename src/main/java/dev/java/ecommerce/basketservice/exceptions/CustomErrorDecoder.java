package dev.java.ecommerce.basketservice.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                return new DataNotFoundException("Produto não encontrado.");
            default:
                return new Exception("Ocorreu um erro ao buscar o produto.");
        }
    }
}
