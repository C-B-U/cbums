import React, { PureComponent } from "react";
import RecruitStudyItem from "./RecruitStudyItem";

class RecruitStudyItemList extends PureComponent {
    render() {
        const { ItemList } = this.props;
        return (
          <React.Fragment>
            <ul>
              {ItemList &&
                ItemList.map((itemdata) => {
                  return (
                    <RecruitStudyItem
                      key={itemdata.projectId}
                      projectId={itemdata.projectId}
                      recruit={itemdata.recruit}
                      name={itemdata.name}
                      producer={itemdata.producer}
                      registerDatetime={itemdata.registerDatetime}
                      registerNumber={itemdata.registerNumber}
                    />
                  );
                })}
            </ul>
          </React.Fragment>
        );
    }
}

export default RecruitStudyItemList;
