import React, { PureComponent } from "react";
import Logo from "../default/Logo";
import Navigation from "../default/Navigation";
import { Default, Mobile } from "../MediaQuery";
import style from "../../css/header.module.css";
import Member from "../default/Member";
class Header extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Default>
          <div className={style['header-wrap']}>
            <Logo logoHeight={36} />
            <Navigation />
            <Member />
          </div>
        </Default>
        <Mobile>
          <div className={style['header-wrap']}>
            <Navigation />
            <Logo logoHeight={36} />
          </div>
        </Mobile>
      </React.Fragment>
    );
  }
}

export default Header;
