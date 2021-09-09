import React, { PureComponent } from "react";
import { Switch, Route } from "react-router";
import SignUpButton from "../signUp/SignUpButton";
import LoginButton from "./LoginButton";

class BoxList extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div class="article__boxList">
          <Switch>
            <Route path="/login" component={LoginButton} />
            <Route path="/signup" component={SignUpButton} />
          </Switch>
          <div class="boxList__schoolName boxList__schoolName1">
            한국산업기술대학교
          </div>
          <div class="boxList__schoolName">
            프로그래밍 동아리
            <span class="boxList__scoholName--emp">씨부엉</span>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default BoxList;
