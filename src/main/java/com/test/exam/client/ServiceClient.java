package com.test.exam.client;

import com.test.exam.client.dto.ClientRequest;
import com.test.exam.client.dto.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "service", url = "${url-service}")
public interface ServiceClient {

	@PostMapping
	ClientResponse bookHouse(@RequestBody ClientRequest request);


}
