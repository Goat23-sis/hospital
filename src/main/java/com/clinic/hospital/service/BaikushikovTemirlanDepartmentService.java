package com.clinic.hospital.service;

import com.clinic.hospital.dto.BaikushikovTemirlanDepartmentDto;
import com.clinic.hospital.entity.BaikushikovTemirlanDepartment;
import com.clinic.hospital.exception.BaikushikovTemirlanResourceNotFoundException;
import com.clinic.hospital.mapper.BaikushikovTemirlanDepartmentMapper;
import com.clinic.hospital.repository.BaikushikovTemirlanDepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BaikushikovTemirlanDepartmentService {

    private final BaikushikovTemirlanDepartmentRepository departmentRepository;
    private final BaikushikovTemirlanDepartmentMapper departmentMapper;

    public List<BaikushikovTemirlanDepartmentDto> getAll() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public BaikushikovTemirlanDepartmentDto getById(Long id) {
        BaikushikovTemirlanDepartment department = departmentRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Department not found with id: " + id));
        return departmentMapper.toDto(department);
    }

    public BaikushikovTemirlanDepartmentDto create(BaikushikovTemirlanDepartmentDto dto) {
        BaikushikovTemirlanDepartment department = departmentMapper.toEntity(dto);
        return departmentMapper.toDto(departmentRepository.save(department));
    }

    public BaikushikovTemirlanDepartmentDto update(Long id, BaikushikovTemirlanDepartmentDto dto) {
        BaikushikovTemirlanDepartment department = departmentRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Department not found with id: " + id));
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        return departmentMapper.toDto(departmentRepository.save(department));
    }

    public void delete(Long id) {
        departmentRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Department not found with id: " + id));
        departmentRepository.deleteById(id);
    }
}