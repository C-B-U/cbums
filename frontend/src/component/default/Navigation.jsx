import React, { PureComponent } from "react";
import NavList from "./NavList";

class Navigation extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <nav>
          <NavList />
        </nav>
      </React.Fragment>
    );
  }
}

export default Navigation;
