import React, { PureComponent } from "react";
import BoxList from "../login/BoxList";
import BoxTitle from "../login/BoxTitle";

class SignUpBox extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <BoxTitle title="회원가입" />
        <BoxList />
      </React.Fragment>
    );
  }
}

export default SignUpBox;
