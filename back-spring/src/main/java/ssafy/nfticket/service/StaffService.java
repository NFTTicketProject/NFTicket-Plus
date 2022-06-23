package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.common.error.CustomException;
import ssafy.nfticket.common.error.ErrorCode;
import ssafy.nfticket.dto.simple.SimpleStaffDto;
import ssafy.nfticket.dto.staff.SimpleStaffImgDto;
import ssafy.nfticket.dto.staff.SimpleStaffNameDto;
import ssafy.nfticket.entity.Staff;
import ssafy.nfticket.dto.response.repository.StaffRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StaffService {

    private final StaffRepository staffRepository;

    @Transactional
    public Staff createStaff(SimpleStaffDto simpleStaffDto) {
        Staff staff = new Staff();
        staff.setName(simpleStaffDto.getName());
        staff.setImageUri(simpleStaffDto.getImageUri());

        staffRepository.save(staff);
        return staff;
    }

    public List<SimpleStaffDto> getAllStaff() {
        List<Staff> allStaffs = staffRepository.findAll();
        List<SimpleStaffDto> allStaffsDto = new ArrayList<>();
        for(Staff staff : allStaffs) {
           SimpleStaffDto newStaffDto = new SimpleStaffDto(staff.getName(), staff.getImageUri());
           allStaffsDto.add(newStaffDto);
        }
        return allStaffsDto;
    }

    public Staff getStaff(Long staffId) {
        return staffRepository.findStaffById(staffId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
    }

    @Transactional
    public Staff updateStaff(Long staffId, SimpleStaffDto simpleStaffDto) {
        Staff staff = getStaff(staffId);
        staff.setName(simpleStaffDto.getName());
        staff.setImageUri(simpleStaffDto.getImageUri());
        staffRepository.save(staff);
        return staff;
    }

    @Transactional
    public Staff updateName(Long staffId, SimpleStaffNameDto simpleStaffNameDto) {
        Staff staff = getStaff(staffId);
        staff.setName(simpleStaffNameDto.getName());
        staffRepository.save(staff);
        return staff;
    }

    @Transactional
    public Staff updateImageUri(Long staffId, SimpleStaffImgDto simpleStaffImgDto) {
        Staff staff = getStaff(staffId);
        staff.setImageUri(simpleStaffImgDto.getImageUri());
        staffRepository.save(staff);
        return staff;
    }
}
