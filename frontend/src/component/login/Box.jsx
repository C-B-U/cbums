import React, { PureComponent } from "react";
import BoxList from "./BoxList";
import BoxTitle from "./BoxTitle";

class Box extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <BoxTitle title="로그인" />
        <BoxList />
      </React.Fragment>
    );
  }
}

export default Box;
