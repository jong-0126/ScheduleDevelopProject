package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.schedule.ScheduleResponseDto;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.repository.MemberRepository;
import com.example.scheduledevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    /**
     * 일정 등록 서비스 로직
     * @param title 제목
     * @param contents 내용
     * @param memberId 유저_id
     * @return
     */
    public ScheduleResponseDto create(String title, String contents, Long memberId) {

        // id로 member 찾기
        Member findMember = memberRepository.findByIdOrElseThrow(memberId);

        // 일정 객체 새로 만들어서 save 메소드로 저장
        Schedule schedule = new Schedule(title, contents, findMember);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents());
    }

    /**
     * 등록된 일정 전체 조회 서비스 로직
     * @return
     */
    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    /**
     * 일정 선택 조회 서비스 로직
     * @param id schedule_id
     * @return
     */
    public ScheduleResponseDto findById(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
    }

    /**
     * 일정 수정 서비스 로직
     * @param id schedule_id
     * @param title 수정할 제목
     * @param contents 수정할 내용
     */
    @Transactional
    public void update(Long id, String title, String contents) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        if(title == null){
            title = findSchedule.getTitle();
        }
        if(contents == null){
            contents = findSchedule.getContents();
        }

        findSchedule.update(title, contents);
    }

    /**
     * 일정 삭제 서비스 로직
     * @param id schedule_id
     */
    @Transactional
    public void delete(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findSchedule);
    }
}
