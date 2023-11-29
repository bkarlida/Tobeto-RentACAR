package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.services.abstracts.BillService;
import com.tobeto.spring.b.services.dtos.requests.bill.AddBillRequest;
import com.tobeto.spring.b.services.dtos.requests.bill.UpdateBillRequest;
import com.tobeto.spring.b.services.dtos.responses.bill.GetBillListResponse;
import com.tobeto.spring.b.services.dtos.responses.bill.GetBillResponse;
import com.tobeto.spring.b.entities.Bill;
import com.tobeto.spring.b.repositories.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/bill")
@AllArgsConstructor
public class BillsController {
    private final BillService billService;

    @GetMapping
    public List<GetBillListResponse> getAll(){
        return billService.getAll();
    }
    @GetMapping("{id}")
    public GetBillResponse getById(@PathVariable int id){
        return billService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddBillRequest addBillRequest){
        billService.add(addBillRequest);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateBillRequest updateBillRequest, @PathVariable int id){
        billService.update(updateBillRequest,id);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        billService.delete(id);
    }
}