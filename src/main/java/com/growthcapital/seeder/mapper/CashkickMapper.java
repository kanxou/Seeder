package com.growthcapital.seeder.mapper;

import com.growthcapital.seeder.dto.CashkickDto;
import com.growthcapital.seeder.entity.Cashkick;
import com.growthcapital.seeder.entity.User;
import com.growthcapital.seeder.enums.CashkickStatus;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class CashkickMapper {

  public static CashkickDto toDto(Cashkick cashkick) {
    CashkickDto cashkickDto = new CashkickDto();
    cashkickDto.setCashkickId(cashkick.getCashkickId());
    cashkickDto.setCashkickName(cashkick.getCashkickName());
    cashkickDto.setStatus(cashkick.getStatus());
    cashkickDto.setMaturity(cashkick.getMaturity());
    cashkickDto.setTerm(cashkick.getTerm());
    cashkickDto.setTotalPayout(cashkick.getTotalPayout());
    cashkickDto.setPaybackAmount(cashkick.getPaybackAmount());

    return cashkickDto;
  }

  public static Cashkick toEntity(CashkickDto cashkickDto, User user) {
    if (cashkickDto == null) {
      return null;
    }

    Cashkick cashkick = new Cashkick();
    cashkick.setCashkickName(cashkickDto.getCashkickName());
    cashkick.setCreatedAt(LocalDateTime.now());
    cashkick.setStatus(cashkickDto.getStatus()==null?CashkickStatus.PENDING:cashkickDto.getStatus());
    setDateTime(cashkick, cashkickDto);
    cashkick.setUser(user);
    cashkick.setTerm(cashkickDto.getTerm());
    cashkick.setTotalPayout(cashkickDto.getTotalPayout());
    cashkick.setPaybackAmount(cashkickDto.getPaybackAmount());

    return cashkick;
  }

  private static void setDateTime(Cashkick cashkick, CashkickDto cashkickDto) {
    LocalDateTime currentTimestamp = LocalDateTime.now();
    cashkick.setCreatedAt(currentTimestamp);
    cashkick.setMaturity(currentTimestamp.toLocalDate().plusMonths(cashkickDto.getTerm()));
  }

}
