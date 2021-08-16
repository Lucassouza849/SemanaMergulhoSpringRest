package com.algaworks.algalog.api.model.input;

import com.algaworks.algalog.domain.model.Destinatario;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaInput {
    @Valid
    @NotNull
    private ClientIdInput client;

    @Valid
    @NotNull
    private DestinatarioInput destinatario;

    @NotNull
    private BigDecimal taxa;
}
