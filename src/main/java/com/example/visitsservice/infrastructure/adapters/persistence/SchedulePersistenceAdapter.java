package com.example.visitsservice.infrastructure.adapters.persistence;


import com.example.visitsservice.domain.filters.ScheduleFilter;
import com.example.visitsservice.domain.model.ScheduleModel;
import com.example.visitsservice.domain.ports.out.SchedulePersistencePort;
import com.example.visitsservice.domain.utils.MyPage;
import com.example.visitsservice.infrastructure.entities.ScheduleEntity;
import com.example.visitsservice.infrastructure.mappers.ScheduleEntityMapper;
import com.example.visitsservice.infrastructure.repositories.mysql.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SchedulePersistenceAdapter implements SchedulePersistencePort {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleEntityMapper scheduleEntityMapper;

    @Override
    public void saveSchedule(ScheduleModel scheduleModel) {
        scheduleRepository.save(scheduleEntityMapper.modelToEntity(scheduleModel));
    }

    @Override
    public boolean existsOverlappingSchedule(ScheduleModel scheduleModel) {
        return scheduleRepository.existsByUserIdAndDateRangeOverlapping(
                scheduleModel.getUserId(),
                scheduleModel.getInitialDate(),
                scheduleModel.getFinalDate());
    }

    @Override
    public MyPage<ScheduleModel> getSchedules(ScheduleFilter filter, Integer page, Integer size, boolean orderAsc) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderAsc ? Sort.Direction.ASC : Sort.Direction.DESC, "initialDate"));
        Page<ScheduleEntity> schedulePage = scheduleRepository.findAllByFilter(
                filter.getUserId(),
                filter.getRealStateId(),
                filter.getInitialDate(),
                filter.getFinalDate(),
                pageable);

        List<ScheduleModel> scheduleModels = scheduleEntityMapper.entityListToModelList(schedulePage.getContent());
        return new MyPage<>(scheduleModels, page, size, orderAsc, schedulePage.getTotalElements());
    }

}
