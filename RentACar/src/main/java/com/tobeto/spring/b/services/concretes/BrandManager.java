package com.tobeto.spring.b.services.concretes;

import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.repositories.BrandRepository;
import com.tobeto.spring.b.services.abstracts.BrandService;
import com.tobeto.spring.b.services.dtos.requests.brand.AddBrandRequest;
import com.tobeto.spring.b.services.dtos.requests.brand.UpdateBrandRequest;
import com.tobeto.spring.b.services.dtos.responses.brand.GetBrandListResponse;
import com.tobeto.spring.b.services.dtos.responses.brand.GetBrandResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    @Override
    public void add(AddBrandRequest addBrandRequest) {
        //List<Brand> brandsWithSameName = brandRepository.findByName(addBrandRequest.getName().trim());
        if (brandRepository.existsByName(addBrandRequest.getName().trim()))
            throw new RuntimeException("Aynı isimle iki marka gönderilemez");
        Brand brand =new Brand();
        brand.setName(addBrandRequest.getName());
        brandRepository.save(brand);
    }

    @Override
    public GetBrandResponse getById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse dto =new GetBrandResponse();
        dto.setName(brand.getName());
        return dto;
    }

    @Override
    public List<GetBrandListResponse> getAll() {
        List<Brand> brandList= brandRepository.findAll();
        List<GetBrandListResponse> getBrandListResponseList=new ArrayList<>();
        for (Brand brand: brandList) {
            GetBrandListResponse response = new GetBrandListResponse();
            response.setName(brand.getName());
            getBrandListResponseList.add(response);
        }
        return getBrandListResponseList;
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest, int id) {
        Optional<Brand> brand= brandRepository.findById(id);
        if (brand.isPresent()){
            Brand foundBrand=brand.get();
            foundBrand.setName(updateBrandRequest.getName());
            brandRepository.save(foundBrand);
        }
    }

    @Override
    public void delete(int id) {
        brandRepository.deleteById(id);
    }

    @Override
    public List<GetBrandListResponse> getByName(String name) {
        List<Brand> brands =  brandRepository.findByName(name);
        List<GetBrandListResponse> response = new ArrayList<>();
        for(Brand brand: brands)
        {
            response.add(new GetBrandListResponse(brand.getName()));
        }
        return response;
    }
    public List<GetBrandListResponse> getByName(String name, int id) {
        List<Brand> brands =  brandRepository.findByNameOrIdEquals(name, id);
        List<GetBrandListResponse> response = new ArrayList<>();
        for (Brand brand: brands) {
            response.add(new GetBrandListResponse(brand.getName()));
        }
        return response;
    }

    @Override
    public List<Brand> search() {
        return brandRepository.search();
    }
  //  public List<Brand> getByFirstnameLike(String name){
    //    return brandRepository.findByFirstNameLike(name);
   // }

}


