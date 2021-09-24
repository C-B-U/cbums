import React, { PureComponent } from "react";
import { Link } from "react-router-dom";
class Member extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className="main__member-wrap">
          <div className="main__member">
            <Link to="/signup">
              <div className="main__member-join">회원가입</div>
            </Link>
            <Link to="/login">
              <div className="main__member-login">로그인</div>
            </Link>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default Member;
