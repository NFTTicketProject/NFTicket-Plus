package ssafy.nfticket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.nfticket.dto.simple.SimpleStaffDto;
import ssafy.nfticket.dto.staff.SimpleStaffImgDto;
import ssafy.nfticket.dto.staff.SimpleStaffNameDto;
import ssafy.nfticket.entity.Staff;
import ssafy.nfticket.service.StaffService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staff")
public class StaffApiController {

    private final StaffService staffService;

    /*
    * 스태프 등록
    * */
    @PostMapping()
    public ResponseEntity<SimpleStaffDto> createStaff(@Valid @RequestBody SimpleStaffDto simpleStaffDto) {
        Staff newStaff = staffService.createStaff(simpleStaffDto);
        SimpleStaffDto staffResponseDto = new SimpleStaffDto(newStaff.getName(), newStaff.getImageUri());
        return ResponseEntity.ok().body(staffResponseDto);
    }

    /*
    * 스태프 전체 조회
    * */
    @GetMapping()
    public ResponseEntity<List<SimpleStaffDto>> getAllStaff() {
        List<SimpleStaffDto> allStaffs = staffService.getAllStaff();
        return ResponseEntity.ok().body(allStaffs);
    }

    /*
    * 스태프 개별 조회
    * */
    @GetMapping("/{staffId}")
    public ResponseEntity<SimpleStaffDto> getStaff(@PathVariable("staffId") Long staffId) {
        Staff staff = staffService.getStaff(staffId);
        return ResponseEntity.ok().body(new SimpleStaffDto(staff.getName(), staff.getImageUri()));
    }

    /*
    * 스태프 이름 조회
    * */
    @GetMapping("/{staffId}/name")
    public ResponseEntity<SimpleStaffNameDto> getStaffName(@PathVariable("staffId") Long staffId) {
        SimpleStaffNameDto staffName = new SimpleStaffNameDto(staffService.getStaff(staffId).getName());
        return ResponseEntity.ok().body(staffName);
    }

    /*
    * 스태프 사진 조회
    * */
    @GetMapping("/{staffId}/image-uri")
    public ResponseEntity<SimpleStaffImgDto> getStaffImageUri(@PathVariable("staffId") Long staffId) {
        SimpleStaffImgDto staffImageUri = new SimpleStaffImgDto(staffService.getStaff(staffId).getImageUri());
        return ResponseEntity.ok().body(staffImageUri);
    }

    /*
    * 스태프 정보 수정
    * */
    @PatchMapping("/{staffId}")
    public ResponseEntity<SimpleStaffDto> updateStaff(@PathVariable("staffId") Long staffId,
                                      @Valid @RequestBody SimpleStaffDto simpleStaffDto) {
        Staff staff = staffService.updateStaff(staffId, simpleStaffDto);
        return ResponseEntity.ok().body(new SimpleStaffDto(staff.getName(), staff.getImageUri()));
    }


    /*
    * 스태프 이름 수정
    * */
    @PatchMapping("/{staffId}/name")
    public ResponseEntity<SimpleStaffNameDto> updateStaffName(@PathVariable("staffId") Long staffId,
                                                 @Valid @RequestBody SimpleStaffNameDto simpleStaffNameDto) {
        SimpleStaffNameDto staffName = new SimpleStaffNameDto(staffService.updateName(staffId, simpleStaffNameDto).getName());
        return ResponseEntity.ok().body(staffName);
    }

    /*
    * 스태프 사진 수정
    * */
    @PatchMapping("/{staffId}/image-uri")
    public ResponseEntity<SimpleStaffImgDto> updateStaffImageUri(@PathVariable("staffId") Long staffId,
                                                     @Valid @RequestBody SimpleStaffImgDto simpleStaffImgDto) {
        SimpleStaffImgDto staffImageUri = new SimpleStaffImgDto(staffService.updateImageUri(staffId, simpleStaffImgDto).getImageUri());
        return ResponseEntity.ok().body(staffImageUri);
    }
}
