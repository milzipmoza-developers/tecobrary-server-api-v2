package common.model;

import com.woowacourse.tecobrary.common.exception.MalformedEmailException;
import com.woowacourse.tecobrary.common.model.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    @DisplayName("성공적으로 Email 객체를 생성한다.")
    @Test
    void constructSuccess() {
        assertDoesNotThrow(() -> new Email("admin@tecobrary.com"));
    }

    @DisplayName("Email 객체 생성에 실패한다. 이메일 형식 오류")
    @Test
    void constructFailedMalformedEmail() {
        assertThrows(MalformedEmailException.class, () -> new Email("admin.com"));
    }
}