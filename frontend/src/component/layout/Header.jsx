import React, { PureComponent } from "react";
import Logo from "../default/Logo";
import Navigation from "../default/Navigation";

class Header extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className="header-wrap">
          <Logo />
          <Navigation />
        </div>
      </React.Fragment>
    );
  }
}

export default Header;
