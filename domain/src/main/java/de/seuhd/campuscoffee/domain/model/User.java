package de.seuhd.campuscoffee.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record User (
        @Nullable Long id, // null when the POS has not been created yet
        @Nullable LocalDateTime createdAt, // set on POS creation
        @Nullable LocalDateTime updatedAt, // set on POS creation and update
        @Pattern(regexp = "\\w+") String loginName,
        @Email String emailAddress,
        @NonNull String firstName,
        @NonNull String lastName
) implements Serializable { // serializable to allow cloning (see TestFixtures class).
    @Serial
    private static final long serialVersionUID = 1L;
}