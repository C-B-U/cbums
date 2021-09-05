  
import React, { PureComponent } from "react";
import ContainerTitle from "./ContainerTitle";
import StudySlide from "./StudySlide";

class StudyMobile extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className="main__study">
          <ContainerTitle title="Recruiting Study" />
          <StudySlide />
        </div>
      </React.Fragment>
    );
  }
}

export default StudyMobile;
