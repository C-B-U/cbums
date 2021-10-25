import React, { PureComponent } from "react";
import Logo from "./Logo";
import Navigation from "./Navigation";
import { Default, Mobile } from "../MediaQuery";
import style from "../../css/header.module.css";
import NavUserMenu from "./NavUserMenu";
class Header extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Default>
          <div className={style['header-wrap']}>
            <Logo logoHeight={36} />
            <Navigation />
            <NavUserMenu />
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
