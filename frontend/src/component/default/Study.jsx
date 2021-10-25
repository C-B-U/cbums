import React, { PureComponent } from "react";
import ContainerTitle from "./ContainerTitle";
import StudySlide from "./StudySlide";
import style from "../../css/default/default.module.css";

class Study extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style['main__study']}>
          <ContainerTitle title="Recruiting Study" />
          <StudySlide />
        </div>
      </React.Fragment>
    );
  }
}

export default Study;
