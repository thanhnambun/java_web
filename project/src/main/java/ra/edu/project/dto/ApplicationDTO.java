package ra.edu.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.edu.project.entity.application.Progress;
import ra.edu.project.entity.application.RequestResult;
import ra.edu.project.validation.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    private int id;

    @Min(value = 1, message = "ID ứng viên phải lớn hơn 0", groups = {
            OnPending.class, OnHandling.class, OnInterviewing.class, OnDone.class, OnReject.class, OnCancel.class
    })
    private int candidateId;

    @Min(value = 1, message = "ID vị trí tuyển dụng phải lớn hơn 0", groups = {
            OnPending.class, OnHandling.class, OnInterviewing.class, OnDone.class, OnReject.class, OnCancel.class
    })
    private int recruitmentPositionId;

    @NotBlank(message = "URL CV không được để trống", groups = {
            OnPending.class, OnHandling.class, OnInterviewing.class, OnDone.class, OnReject.class, OnCancel.class
    })
    @Pattern(regexp = "^(http|https)://.*", message = "URL CV không hợp lệ", groups = {
            OnPending.class, OnHandling.class, OnInterviewing.class, OnDone.class, OnReject.class, OnCancel.class
    })
    private String cvUrl;

    @NotNull(message = "Tiến trình không được để trống", groups = {
            OnPending.class, OnHandling.class, OnInterviewing.class, OnDone.class, OnReject.class, OnCancel.class
    })
    private Progress progress;

    @NotNull(message = "Thời gian yêu cầu phỏng vấn không được để trống", groups = {
            OnHandling.class, OnInterviewing.class, OnDone.class, OnReject.class, OnCancel.class
    })
    @FutureOrPresent(message = "Thời gian yêu cầu phỏng vấn phải từ hiện tại trở đi", groups = {
            OnHandling.class, OnInterviewing.class, OnDone.class, OnReject.class, OnCancel.class
    })
    private LocalDateTime interviewRequestDate;

    @NotNull(message = "Kết quả yêu cầu phỏng vấn không được để trống", groups = {
            OnHandling.class, OnInterviewing.class, OnDone.class, OnReject.class, OnCancel.class
    })
    private RequestResult interviewRequestResult;

    @NotBlank(message = "Link phỏng vấn không được để trống", groups = {OnInterviewing.class, OnDone.class})
    @Pattern(regexp = "^(http|https)://.*", message = "Link phỏng vấn không hợp lệ", groups = {OnInterviewing.class, OnDone.class})
    private String interviewLink;

    @NotNull(message = "Thời gian phỏng vấn không được để trống", groups = {OnInterviewing.class, OnDone.class})
    @FutureOrPresent(message = "Thời gian phỏng vấn phải từ hiện tại trở đi", groups = {OnInterviewing.class, OnDone.class})
    private LocalDateTime interviewTime;

    @NotBlank(message = "Ghi chú kết quả phỏng vấn không được để trống", groups = {OnDone.class})
    @Size(max = 1000, message = "Ghi chú kết quả phỏng vấn không được vượt quá 1000 ký tự", groups = {OnDone.class})
    private String interviewResultNote;

    @NotBlank(message = "Lý do hủy không được để trống", groups = {OnReject.class, OnCancel.class})
    @Size(max = 1000, message = "Lý do hủy không được vượt quá 1000 ký tự", groups = {OnReject.class, OnCancel.class})
    private String destroyReason;
}