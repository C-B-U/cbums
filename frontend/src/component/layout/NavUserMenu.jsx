import React, { PureComponent } from "react";
import { Link } from "react-router-dom";
import style from "../../css/header.module.css";

class NavUserMenu extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style["main__member-wrap"]}>
          <div className={style.main__member}>
            <Link to="/signup">
              <div className={style["main__member-join"]}>회원가입</div>
            </Link>
            <Link to="/login">
              <div className={style["main__member-login"]}>로그인</div>
            </Link>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default NavUserMenu;
