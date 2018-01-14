package pusz.name.smsgateway.mapper;

import org.springframework.stereotype.Component;
import pusz.name.smsgateway.domain.SmsRequest;
import pusz.name.smsgateway.domain.SmsRequestDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SmsRequestMapper {

    public SmsRequest mapToSmsRequest(final SmsRequestDto smsRequestDto) {
        return new SmsRequest(
                smsRequestDto.getPhoneNumber(),
                smsRequestDto.getMessage(),
                LocalDateTime.now());
    }

    public SmsRequestDto mapToSmsRequestDto(final SmsRequest smsRequest) {
        return new SmsRequestDto(
                smsRequest.getId(),
                smsRequest.getPhoneNumber(),
                smsRequest.getMessage(),
                smsRequest.getStatus(),
                smsRequest.isStatusFinal());
    }

    public List<SmsRequestDto> mamToSmsRequestDtoList(final List<SmsRequest> smsRequests) {
        return smsRequests.stream()
                .map(s -> mapToSmsRequestDto(s))
                .collect(Collectors.toList());
    }
}
