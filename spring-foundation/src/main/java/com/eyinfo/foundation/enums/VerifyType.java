package com.eyinfo.foundation.enums;

import java.io.Serializable;

public enum VerifyType implements Serializable {
    //从RequestHeader中验证
    header,
    //从RequestParam中验证
    param,
    //从RequestHeader和RequestParam中验证
    both
}
