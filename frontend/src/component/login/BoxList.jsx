import React, { PureComponent } from "react";
import LoginButton from "./LoginButton";

class BoxList extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div class="article__boxList">
          <LoginButton />
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
