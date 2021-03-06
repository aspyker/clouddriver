/*
 * Copyright 2016 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.clouddriver.kubernetes.deploy.description.job

import com.netflix.spinnaker.clouddriver.kubernetes.deploy.description.KubernetesAtomicOperationDescription
import com.netflix.spinnaker.clouddriver.kubernetes.deploy.description.servergroup.KubernetesContainerDescription
import com.netflix.spinnaker.clouddriver.kubernetes.deploy.description.servergroup.KubernetesVolumeSource
import groovy.transform.AutoClone
import groovy.transform.Canonical

@AutoClone
@Canonical
class RunKubernetesJobDescription extends KubernetesAtomicOperationDescription {
  String application
  String stack
  String freeFormDetails
  String namespace
  Integer parallelism
  Integer completions
  Integer activeDeadlineSeconds
  KubernetesJobRestartPolicy restartPolicy
  List<String> loadBalancers
  List<KubernetesContainerDescription> containers
  List<KubernetesVolumeSource> volumeSources
}

enum KubernetesJobRestartPolicy {
  Never, OnFailure

  static KubernetesJobRestartPolicy fromString(String policy) {
    switch (policy) {
      case "Never":
        return Never
      case null: // default policy is "OnFailure"
      case "OnFailure":
        return OnFailure
      default:
        throw new IllegalArgumentException("Unsupported restart policy ${policy}".toString())
    }
  }
}
