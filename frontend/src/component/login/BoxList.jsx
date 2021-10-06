import React, { PureComponent } from "react";
import { Switch, Route } from "react-router";
import SignUpButton from "../signUp/SignUpButton";
import LoginButton from "./LoginButton";
import style from "../../css/login/login.module.css";
class BoxList extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style.article__boxList}>
          <Switch>
            <Route path="/login" component={LoginButton} />
            <Route path="/signup" component={SignUpButton} />
          </Switch>
          <div className={`${style.boxList__schoolName} ${style.boxList__schoolName1}`}>
            한국산업기술대학교
          </div>
          <div className={style.boxList__schoolName}>프로그래밍 동아리 씨부엉</div>
        </div>
      </React.Fragment>
    );
  }
}

export default BoxList;
