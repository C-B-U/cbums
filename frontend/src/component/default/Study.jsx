import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
import { Link } from "react-router-dom";
import ContainerTitle from "./ContainerTitle";

class Study extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div class="main__study">
          <ContainerTitle title="Recruiting Study" />
        </div>
      </React.Fragment>
    );
  }
}

export default Study;
