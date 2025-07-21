import { useState } from "react";
import "../styles/home.css";
import { createCrud } from "../api/createCrud";

function Home() {
  const [entities, setEntities] = useState([
    {
      entityName: "",
      attribute: [{ name: "", type: "String", nullable: true }],
      relation: [],
    },
  ]);

  const handleEntityNameChange = (index, value) => {
    const updated = [...entities];
    updated[index].entityName = value;
    setEntities(updated);
  };

  const handleAttributeChange = (entityIndex, attrIndex, field, value) => {
    const updated = [...entities];
    updated[entityIndex].attribute[attrIndex][field] =
      field === "nullable" ? value.target.checked : value;
    setEntities(updated);
  };

  const addAttribute = (entityIndex) => {
    const updated = [...entities];
    updated[entityIndex].attribute.push({
      name: "",
      type: "String",
      nullable: true,
    });
    setEntities(updated);
  };

  const removeAttribute = (entityIndex, attrIndex) => {
    const updated = [...entities];
    updated[entityIndex].attribute.splice(attrIndex, 1);
    setEntities(updated);
  };

  const addEntity = () => {
    setEntities([
      ...entities,
      {
        entityName: "",
        attribute: [{ name: "", type: "String", nullable: true }],
        relation: [],
      },
    ]);
  };

  const removeEntity = (index) => {
    const updated = [...entities];
    updated.splice(index, 1);
    setEntities(updated);
  };

  const handleRelationChange = (entityIndex, relationIndex, field, value) => {
    const updated = [...entities];
    updated[entityIndex].relation[relationIndex][field] = value;
    setEntities(updated);
  };

  const addRelation = (entityIndex) => {
    const updated = [...entities];
    if (!updated[entityIndex].relation) {
      updated[entityIndex].relation = [];
    }
    updated[entityIndex].relation.push({
      relationType: "onetomany", // lowercase for consistency
      targetEntity: "",
    });
    setEntities(updated);
  };

  const removeRelation = (entityIndex, relationIndex) => {
    const updated = [...entities];
    updated[entityIndex].relation.splice(relationIndex, 1);
    setEntities(updated);
  };

  const handleSubmit = async () => {
    try {
      console.log(entities);
      const response = await createCrud(entities);
      console.log("responce: ", response);
      if (response.status == 200) {
        // Create a blob from the response
        const blob = new Blob([response.data], { type: 'application/zip' });

        // Create a temporary link to trigger download
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = 'generated_project.zip';
        document.body.appendChild(link);
        link.click();
        link.remove();
        window.URL.revokeObjectURL(link.href); // Clean up blob URL

        alert('✅ Project generated and downloaded successfully!');
        console.log("Backend Response:", response.data);
      } else if (response.status === 404) {
        alert(' ZIP file not found on the server.');
      } else {
        alert("Failed to generate backend code.");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("Error sending data to backend.");
    }
  };

  return (
    <div className="home">
      <div className="title">
        <h1>Auto CRUD</h1>
        <h3>Generates the Spring Backend CRUD with just a few clicks</h3>
      </div>

      <div className="form-section">
        {entities.map((entity, entityIndex) => (
          <div key={entityIndex} className="entity-card">
            <div className="entity-header">
              <input
                type="text"
                placeholder="Entity Name"
                value={entity.entityName}
                onChange={(e) =>
                  handleEntityNameChange(entityIndex, e.target.value)
                }
                className="input entity-input"
              />
              {entities.length > 1 && (
                <button
                  onClick={() => removeEntity(entityIndex)}
                  className="button remove-entity-btn"
                >
                  ✖
                </button>
              )}
            </div>

            <div className="attribute-list">
              {entity.attribute.map((attr, attrIndex) => (
                <div key={attrIndex} className="attribute-row">
                  <input
                    type="text"
                    placeholder="Attribute Name"
                    value={attr.name}
                    onChange={(e) =>
                      handleAttributeChange(
                        entityIndex,
                        attrIndex,
                        "name",
                        e.target.value
                      )
                    }
                    className="input attribute-input"
                  />

                  <select
                    value={attr.type}
                    onChange={(e) =>
                      handleAttributeChange(
                        entityIndex,
                        attrIndex,
                        "type",
                        e.target.value
                      )
                    }
                    className="dropdown"
                  >
                    <option value="String">String</option>
                    <option value="int">int</option>
                    <option value="boolean">boolean</option>
                  </select>

                  <label className="nullable-label">
                    <input
                      type="checkbox"
                      checked={attr.nullable}
                      onChange={(e) =>
                        handleAttributeChange(
                          entityIndex,
                          attrIndex,
                          "nullable",
                          e
                        )
                      }
                    />
                    Nullable
                  </label>

                  {entity.attribute.length > 1 && (
                    <button
                      onClick={() => removeAttribute(entityIndex, attrIndex)}
                      className="button remove-attr-btn"
                    >
                      ✖
                    </button>
                  )}
                </div>
              ))}
              <button
                onClick={() => addAttribute(entityIndex)}
                className="button add-attr-btn"
              >
                + Add Attribute
              </button>
            </div>

            <div className="relation-list">
              <h4>Relations</h4>
              {entity.relation?.map((rel, relIndex) => (
                <div key={relIndex} className="relation-row">
                  <select
                    value={rel.relationType}
                    onChange={(e) =>
                      handleRelationChange(
                        entityIndex,
                        relIndex,
                        "relationType",
                        e.target.value
                      )
                    }
                    className="dropdown"
                  >
                    <option value="onetomany">OneToMany</option>
                    <option value="manytoone">ManyToOne</option>
                  </select>

                  <input
                    type="text"
                    placeholder="Target Entity"
                    value={rel.targetEntity}
                    onChange={(e) =>
                      handleRelationChange(
                        entityIndex,
                        relIndex,
                        "targetEntity",
                        e.target.value
                      )
                    }
                    className="input"
                  />

                  <button
                    onClick={() => removeRelation(entityIndex, relIndex)}
                    className="button remove-attr-btn"
                  >
                    ✖
                  </button>
                </div>
              ))}
              <button
                onClick={() => addRelation(entityIndex)}
                className="button add-relation-btn"
              >
                + Add Relation
              </button>
            </div>
          </div>
        ))}

        <button onClick={addEntity} className="button add-entity-btn">
          + Add Another Entity
        </button>

        <button onClick={handleSubmit} className="button submit-btn">
          Generate Backend 
        </button>
      </div>
    </div>
  );
}

export default Home;
