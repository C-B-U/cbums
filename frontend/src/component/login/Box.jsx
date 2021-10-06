import React, { PureComponent } from "react";
import BoxList from "./BoxList";
import BoxTitle from "./BoxTitle";
import style from "../../css/login/login.module.css";

class Box extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <BoxTitle
          title="로그인"
          titleClass={style.article__boxTitle}
          imageClass={style.article__boxDeco}
        />
        <BoxList />
      </React.Fragment>
    );
  }
}

export default Box;
