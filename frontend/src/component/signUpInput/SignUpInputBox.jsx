import React, { PureComponent } from "react";

import BoxTitle from "../login/BoxTitle";
import InputContent from "./InputContent";

class SignUpBox extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <BoxTitle
          title="추가 정보 입력"
          titleClass="article__boxTitle"
          imageClass="article__boxDeco"
        />
        <InputContent />
      </React.Fragment>
    );
  }
}

export default SignUpBox;
