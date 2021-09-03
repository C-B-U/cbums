import React, { PureComponent } from "react";
import { Link } from "react-router-dom";
class Member extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div class="main__member-wrap">
          <div class="main__member">
            <Link to="/">
              <div class="main__member-join">회원가입</div>
            </Link>
            <Link to="/">
              <div class="main__member-login">로그인</div>
            </Link>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default Member;
