package com.eCom.dhailer.service;


import com.eCom.dhailer.dto.request.RequestSupplierDto;
import com.eCom.dhailer.dto.responce.ResponceSupplierDto;

import java.util.List;

public interface SupplierService {

    public List<ResponceSupplierDto> getAll();

    public void create(RequestSupplierDto requestSupplierDto);

}
