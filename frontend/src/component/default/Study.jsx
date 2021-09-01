import React, { PureComponent } from "react";
import { Link } from "react-router-dom";
import ContainerTitle from "./ContainerTitle";
import Slide from "./Slide";

class Study extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div class="main__study">
          <ContainerTitle title="Recruiting Study" />
          <Slide />
        </div>
      </React.Fragment>
    );
  }
}

export default Study;
